package com.project.hikes.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.hikes.entity.HikeReview;
import com.project.hikes.entity.HikeTrail;
import com.project.hikes.entity.HikeUser;
import com.project.hikes.service.HikeService;

@Controller
@RequestMapping("/hike")
public class HikeController {

	@Autowired
	private HikeService hikeService;



	//add init binder to convert trim input strings
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

	}

	//display only recent trails on home page
	@RequestMapping("/home")
	public String home(Model model){
		//get the trails from service
		List<HikeTrail> trails = hikeService.getRecentHikeTrails();
		model.addAttribute("trails", trails);

		//System.out.println("User role is "+);
		//System.out.println("total trails found = "+trails.size());
		//System.out.println("the latest is "+trails.get(0).getName());
		return "home";
	}

	//display the whole trails
	@RequestMapping("/list")
	public String allTrails(Model model){
		List<HikeTrail> trails = hikeService.getAllHikeTrails();
		model.addAttribute("trails", trails);

		return "trails/trails";
	}

	//display form to add new trail
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		HikeTrail trail = new HikeTrail();
		model.addAttribute("trail", trail);
		return "trails/trail-form";
	}

	//process form to add new trail
	@PostMapping("/saveHikeTrail")
	public String saveHikeTrail(@Valid @ModelAttribute("trail")HikeTrail hikeTrail,
			BindingResult theBindingResult,
			final RedirectAttributes redirectAttrs){
		
		//System.out.println("chechin id in save.... "+theId);

		/*System.out.println("HIke Name: |"+newHikeTrail.getName()+"|");
		System.out.println("the Binding Result: "+theBindingResult);
		System.out.println("\n\n\n");*/

		if(theBindingResult.hasErrors()){
			
			//when there is issue with update action
			/*if(theId==0){
				HikeTrail trail = hikeService.getHikeTrail(theId);
				model.addAttribute("trail", trail);
			}*/
			return "trails/trail-form";
		}

		hikeService.saveHikeTrail(hikeTrail);
		redirectAttrs.addFlashAttribute("message", "Successfully saved a hike trail - "+hikeTrail.getName());

		//return "redirect:/hike/home";
		return "redirect:/hike/list";
	}

	//display form to update the existing trail
	@GetMapping("/showFormForInfo/{trailId}")
	public String showFormForInfo(@PathVariable int trailId, Model model){

		//find trail info
		HikeTrail trail = hikeService.getHikeTrail(trailId);
		model.addAttribute("trail", trail);

		//gather all the reviews for that particular trail
		List<HikeReview> reviewsList = hikeService.getRelatedReviews(trailId);
		model.addAttribute("reviewsList", reviewsList);
		//System.out.println("total reviews found: "+reviewsList.size());

		//also find the authenticated user to add on review
		HikeUser hikeUser = hikeService.getAuthenticatedCurrentUser();
		//System.out.println("Adding authenticated user "+hikeUser.getEmail());
		model.addAttribute("authenticatedUser", hikeUser);

		return "trails/trail-detail";
	}

	//show form to update the hike trail
	@GetMapping("/editHikeTrail")
	public String editHikeTrail(@RequestParam("id") int theId, Model model){
		HikeTrail trail = hikeService.getHikeTrail(theId);
		model.addAttribute("trail", trail);
		//return "trails/trail-detail-edit";
		return "trails/trail-form";
	}

	//delete trail info
	@GetMapping("/deleteHikeTrail")
	public String deleteHikeTrail(@RequestParam("id")int theId,
			final RedirectAttributes redirectAttrs){
		hikeService.deleteHikeTrail(theId);

		redirectAttrs.addFlashAttribute("message", "Successfully deleted a hike trail");
		return "redirect:/hike/list";
	}

	@RequestMapping("/about")
	public String about(){
		return "trails/about";
	}

	//show review form to add review for the hike trail
	@GetMapping("/showReviewForm/{trailId}")
	public String showReviewForm(@PathVariable int trailId, Model model){
		//first find Trail info and add to model
		HikeTrail trail = hikeService.getHikeTrail(trailId);
		model.addAttribute("trail", trail);

		HikeReview review = new HikeReview();
		model.addAttribute("review", review);

		//System.out.println("Adding trail  for review...");
		return "trails/review-form";
	}

	//save review for Hike Trail
	@PostMapping("/saveHikeTrailReview/{trailId}")
	public String saveHikeTrailReview(@Valid @ModelAttribute("review")HikeReview newReview,
			BindingResult theBindingResult,
			@PathVariable int trailId,
			Model model,
			final RedirectAttributes redirectAttrs){
		
		//System.out.println("the Binding Result: "+theBindingResult);
		if(theBindingResult.hasErrors()){
			//return "redirect:/hike/showReviewForm/{trailId}";
			
			//if error, then first find Trail info and add to model
			HikeTrail trail = hikeService.getHikeTrail(trailId);
			model.addAttribute("trail", trail);
			return "trails/review-form";
		}

		hikeService.saveReview(trailId, newReview);
		redirectAttrs.addFlashAttribute("message", "Successfully added review for a hike trail");
		return "redirect:/hike/showFormForInfo/{trailId}";
	}

	//delete review 
	@GetMapping("/deleteHikeReview/{trailId}")
	public String deleteHikeReview(@RequestParam("id")int reviewId,
			@PathVariable int trailId,
			final RedirectAttributes redirectAttrs){

		//System.out.println("dleeting review id "+reviewId);
		hikeService.deleteHikeReview(reviewId);
		redirectAttrs.addFlashAttribute("message", "Successfully deleted review for a hike trail");
		return "redirect:/hike/showFormForInfo/{trailId}";
	}

}
