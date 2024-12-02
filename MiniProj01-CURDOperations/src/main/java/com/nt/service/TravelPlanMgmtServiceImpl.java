package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.config.AppConfigProperties;
import com.nt.entity.PlanCategory;
import com.nt.entity.TravelPlan;
import com.nt.repository.IPlanCategoryRepository;
import com.nt.repository.ITravelPlanRepository;
@Service
public class TravelPlanMgmtServiceImpl implements ITravelPlanMgmtService {
@Autowired
private ITravelPlanRepository travelPlanRepo;
@Autowired
private IPlanCategoryRepository planCategoryRepo;

private Map<String,String> messages;

public TravelPlanMgmtServiceImpl(AppConfigProperties props) {
	messages=props.getMessages();
}
	@Override
	public String registerTravelPlan(TravelPlan plan) {
		//save object
		TravelPlan saved=travelPlanRepo.save(plan);
	/*	if(saved.getPlanId()!=null)
			return "travelPlan is saved with the id value"+saved.getPlanId();
		else
			
		      return  saved.getPlanId()!=null?"Travel plan is saved with id value"+saved.getPlanId():"problem is saving tour plan";*/
		return saved.getPlanId()!=null?messages.get("save-success")+saved.getPlanId():messages.get("save-failure");
	}

	@Override
	public Map<Integer, String> getTravelPlanCategories() {
		//get all travel plan categories
		List<PlanCategory> list=planCategoryRepo.findAll();
		Map<Integer,String> categoriesMap=new HashMap<Integer,String>();
		list.forEach(category->{categoriesMap.put(category.getCategoryId(),category.getCategoryName());
			});
		return categoriesMap;
	}

	@Override
	public List<TravelPlan> showAllTravelPlans() {
		
		return travelPlanRepo.findAll();
	}

	@Override
	public TravelPlan showTravelPlanById(Integer planId) {
		
		return travelPlanRepo.findById(planId).orElseThrow(()->new IllegalArgumentException(messages.get("find-by-id-failure")));
		
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		Optional<TravelPlan> opt=travelPlanRepo.findById(plan.getPlanId());
		if(opt.isPresent()) {
			//update the object
			travelPlanRepo.save(plan);
			return plan.getPlanId()+messages.get("update-success");
			
		}
		else
		return plan.getPlanId()+messages.get("update-failure");
	}

	@Override
	public String deleteTravelPlan(Integer planId) {
		Optional<TravelPlan> opt=travelPlanRepo.findById(planId);
		if(opt.isPresent()) {
			//update the object
			travelPlanRepo.deleteById(planId);
			return planId+messages.get("delete-success");
		}
		else
		return planId+messages.get("delete-failure");
	}
	

	@Override
	public String changeTravelPlanStatus(Integer planId, String status) {
		Optional<TravelPlan> opt=travelPlanRepo.findById(planId);
		if(opt.isPresent()) {
			TravelPlan plan=opt.get();
			plan.setActivateSW(status);
			travelPlanRepo.save(plan);
			return planId+messages.get("status-change-success");
			
		}
		else
		return planId+messages.get("status-change-failure");
	}

}