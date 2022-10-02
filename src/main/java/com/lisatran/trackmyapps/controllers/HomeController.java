package com.lisatran.trackmyapps.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lisatran.trackmyapps.models.Archive;
import com.lisatran.trackmyapps.models.Comment;
import com.lisatran.trackmyapps.models.Interview;
import com.lisatran.trackmyapps.models.Job;
import com.lisatran.trackmyapps.models.LoginUser;
import com.lisatran.trackmyapps.models.User;
import com.lisatran.trackmyapps.services.ArchiveService;
import com.lisatran.trackmyapps.services.CommentService;
import com.lisatran.trackmyapps.services.InterviewService;
import com.lisatran.trackmyapps.services.JobService;
import com.lisatran.trackmyapps.services.UserService;



@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private InterviewService interviewService;
	
	@Autowired ArchiveService archiveService;
	
	// LOGIN AND REGISTRATION
	
		@GetMapping("/")
		public String index(Model model) {
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		@PostMapping("/register")
		public String processRegister(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
			userService.register(newUser, result);
			
			if(result.hasErrors()) {
				model.addAttribute("newLogin", new LoginUser());
				return "index.jsp";
			}
			
			session.setAttribute("userId", newUser.getId());
			return "redirect:/dashboard";
		}
		
		@PostMapping("/login")
		public String processLogin(@Valid @ModelAttribute("newLogin")LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
			User user = userService.login(newLogin, result);

			if(result.hasErrors()) {
				model.addAttribute("newUser", new User());
				return "index.jsp";
			}
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		}
		// LOGOUT
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
		
		// DASHBOARD
		
		@GetMapping("/dashboard")
		public String dashboard(HttpSession session, Model model, @ModelAttribute("job")Job job) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userService.findById(userId));
			User user = userService.oneUser((Long) session.getAttribute("userId"));
			model.addAttribute("user", user);
			
			List<Job> jobList = jobService.jobSort();
			model.addAttribute("jobList", jobList);
			List<Interview> interviewList = interviewService.interviewSort();
			model.addAttribute("interviewList", interviewList);
			List<Archive> archiveList = archiveService.allArchives();
			model.addAttribute("archiveList", archiveList);
			return "dashboard.jsp";
		}
		
		// CREATE JOB
		
		@PostMapping("/jobs/add")
		public String renderAddjob(@Valid @ModelAttribute("job")Job job, BindingResult result, HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			if(result.hasErrors()) {
				List<Job> jobList = jobService.allJobs();
				model.addAttribute("jobList", jobList);
				return "dashboard.jsp";
			}else {
				jobService.createJob(job);
				return "redirect:/dashboard";
			}
		}
		
		// EDIT JOB
		
		@GetMapping("/jobs/{id}/edit")
		public String renderEditJob(@PathVariable("id")Long id, Model model, HttpSession session) {
			Job job = jobService.oneJob(id);
			if(job.getUser().getId() != (Long)session.getAttribute("userId")) {
				return "redirect:/dashboard";
			}
			model.addAttribute("job", jobService.oneJob(id));
			return "editJob.jsp";
		}
		
		@PutMapping("jobs/{id}/edit")
		public String processEditJob(@Valid @ModelAttribute("job")Job job, BindingResult result, HttpSession session, Model model, @PathVariable("id")Long id) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			if(result.hasErrors()) {
				model.addAttribute("job", jobService.oneJob(id));
				System.out.println(result);
				return "editJob.jsp";
			}else {
				jobService.updateJob(job);
				model.addAttribute("job", jobService.oneJob(id));
				Long jobId = job.getId();
				return "redirect:/jobs/" + jobId;
			}
		}
	
		// SHOW JOB
		@GetMapping("/jobs/{id}")
		public String renderShowJob(HttpSession session, @PathVariable("id")Long id, Model model, @ModelAttribute("comment")Comment comment){
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Comment newComment = new Comment();
			model.addAttribute("job", jobService.oneJob(id));
			model.addAttribute("user", userService.oneUser(id));
			model.addAttribute("newComment", newComment);
			List<Comment> commentList = commentService.allComments();
			model.addAttribute("commentList", commentList);
			List<Interview> interviewList = interviewService.allInterviews();
			model.addAttribute("interviewList", interviewList);
			return "showJob.jsp";
		}
		
		// ADD COMMENT
		@PostMapping("comments/add")
		public String processAddComment(@Valid @ModelAttribute("newComment")Comment newComment, BindingResult result, HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			if(result.hasErrors()) {
//				model.addAttribute("job", jobService.oneJob(id));
				List<Comment> commentList = commentService.allComments();
				model.addAttribute("commentList", commentList);
				return "showJob.jsp";
			}else {
				commentService.createComment(newComment);
				Long jobId = newComment.getJob().getId();
				return "redirect:/jobs/" + jobId;
			}
		}
		
		// DELETE COMMENT
		@DeleteMapping("/comments/{id}/delete")
		public String deleteComment(@PathVariable("id")Long id, HttpSession session, Model model){
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			model.addAttribute("job", jobService.oneJob(id));

			Long jobId = commentService.oneComment(id).getJob().getId();
			commentService.deleteComment(commentService.oneComment(id));
			return "redirect:/jobs/" + jobId;
			
		}
		
		
		// DELETE JOB
		@DeleteMapping("/jobs/{id}/delete")
		public String deleteJob(@PathVariable("id")Long id, HttpSession session) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			jobService.deleteJob(jobService.oneJob(id));
			return "redirect:/dashboard";
		}
		
		// ADD INTERVIEW
		@GetMapping("/interviews/add")
		public String addInterview(HttpSession session, Model model, @ModelAttribute("interview")Interview interview) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			List<Job> jobList = jobService.allJobs();
			model.addAttribute("jobList", jobList);
			return "addInterview.jsp";
		}
		
		@PostMapping("/interviews/add")
		public String processAddInterview(@Valid @ModelAttribute("interview")Interview interview, BindingResult result, HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			if(result.hasErrors()) {
				List<Job> jobList = jobService.allJobs();
				model.addAttribute("jobList", jobList);

				return "addInterview.jsp";
			}else {
				interviewService.createInterview(interview);

				return "redirect:/dashboard";
			}
		}
		
		// EDIT INTERVIEW
		
		@GetMapping("interviews/{id}/edit")
		public String editInterview(@PathVariable("id")Long id, Model model, HttpSession session) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
//			Interview interview = interviewService.oneInterview(id);
//			model.addAttribute("job", jobService.oneJob(id));
//			if(jobService.oneJob(id).getUser().getId() != (Long)session.getAttribute("userId")) {
//				System.out.println(jobService.oneJob(id).getUser().getId());
//				System.out.println((Long)session.getAttribute("userId"));
//				return "redirect:/dashboard";
//			}
			
			List<Job> jobList = jobService.allJobs();
			model.addAttribute("jobList", jobList);
			model.addAttribute("interview", interviewService.oneInterview(id));
			return "editInterview.jsp";
		}
		
		@PutMapping("interviews/{id}/edit")
		public String processEditInterview(@Valid @ModelAttribute("interview")Interview interview, BindingResult result, HttpSession session, Model model, @PathVariable("id")Long id) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			if(result.hasErrors()) {
				return "editInterview.jsp";
			}else {
				interviewService.updateInterview(interview);
				model.addAttribute("job", jobService.oneJob(id));
				Long jobId = interviewService.oneInterview(id).getJob().getId();
				return "redirect:/jobs/" + jobId;
			}
		}
		
		
		// DELETE INTERVIEW
		@DeleteMapping("/interviews/{id}/delete")
		public String deleteInterview(@PathVariable("id")Long id, HttpSession session, Model model){
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}

			model.addAttribute("job", jobService.oneJob(id));
			Long jobId = interviewService.oneInterview(id).getJob().getId();
			interviewService.deleteInterview(interviewService.oneInterview(id));
			return "redirect:/jobs/" + jobId;
			
		}
		
		// FAVORITE A JOB
		@PutMapping("/jobs/favorite/{id}")
		public String favorite(@PathVariable("id")Long id, HttpSession session) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.oneUser(userId);
			
			jobService.favoriteJob(id, user);
			return "redirect:/dashboard";
		}
		
		// UNFAVORITE A JOB
		@PutMapping("jobs/unfavorite/{id}")
		public String unfavorite(@PathVariable("id")Long id, HttpSession session) {
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.oneUser(userId);
			jobService.unfavoriteJob(id, user);
			return "redirect:/dashboard";
		}
		
		// EDIT PROFILE PAGE
		@GetMapping("users/{id}/edit")
		public String editProfile(HttpSession session, @PathVariable("id")Long id, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userService.findById(userId));
			return "editProfile.jsp";
		}
		
		@PutMapping("users/{id}/edit")
		public String processEditUser(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session, Model model, @PathVariable("id")Long id) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			if(result.hasErrors()) {
				model.addAttribute("user", userService.oneUser(id));
				System.out.println(result);
				return "editProfile.jsp";
			}else {
				userService.updateUser(user);
				return "redirect:/dashboard";
			}
		}
		
		// ADD TO ARCHIVES
		@PostMapping("/archives/add")
		public String addArchive(HttpSession session, Model model, @ModelAttribute("archive")Archive archive) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			archiveService.createArchive(archive);
			return "redirect:/dashboard";
		}
		
		// VIEW ARCHIVES
		@GetMapping("/archives")
		public String archives(HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
//			List<Job> jobList = jobService.jobSort();
//			model.addAttribute("jobList", jobList);
//			List<Interview> interviewList = interviewService.interviewSort();
//			model.addAttribute("interviewList", interviewList);
			List<Archive> archivedJobList = archiveService.createArchivedJobs();
			model.addAttribute("archivedJobList", archivedJobList);
			List<Archive> archivedInterviewList = archiveService.createArchivedInterviews();
			model.addAttribute("archivedInterviewList", archivedInterviewList);
			return "archives.jsp";
		}
		
		// UNARCHIVE JOB
		@DeleteMapping("archives/{id}/delete")
		public String unarchive(@PathVariable("id")Long id, HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			model.addAttribute("job", jobService.oneJob(id));
			archiveService.deleteArchive(archiveService.oneArchive(id));
			return "redirect:/dashboard";
		}

}
