package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.entity.TravelPlan;

public interface ITravelPlanMgmtService {
public String registerTravelPlan(TravelPlan plan);//save operation
public Map<Integer,String> getTravelPlanCategories();//select operation
public List<TravelPlan> showAllTravelPlans();//select operation
public TravelPlan showTravelPlanById(Integer planId);//for edit operation form launch
public String updateTravelPlan(TravelPlan plan);//for edit operation
public String deleteTravelPlan(Integer planId);//for delete operation
public String changeTravelPlanStatus(Integer planId,String status);//for soft deletion activity

}
