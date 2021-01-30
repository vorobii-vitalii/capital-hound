package com.site.capitalhound.controller;

import com.site.capitalhound.helpers.CoordinatesHelper;
import com.site.capitalhound.service.CapitalHoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CapitalHoundController {
    private final CapitalHoundService capitalHoundService;

    @GetMapping("/")
    public String showWelcomePage() {
        return "index";
    }

    @GetMapping("/result")
    public String handleIncomingRequest(@RequestParam("address") String address, Model model) {
        var result = capitalHoundService.getResultByProvidedAddress(address);

        final int calculatedDistanceBetweenCapitalAndOrigin =
                CoordinatesHelper.calculateDistanceInKilometer(
                    result.getAssociatedCapitalLatitude(), result.getAssociatedCapitalLongitude(),
                    result.getEnteredAddressLatitude(), result.getEnteredAddressLongitude()
                );

        model.addAttribute("enteredAddress", address);
        model.addAttribute("countryName", result.getCountryName());
        model.addAttribute("capitalName", result.getCapitalName());
        model.addAttribute("area", result.getArea());
        model.addAttribute("population", result.getPopulation());
        model.addAttribute("flagUrl", result.getFlagUrl());
        model.addAttribute("region", result.getRegion());
        model.addAttribute("distance", calculatedDistanceBetweenCapitalAndOrigin);
        model.addAttribute("capitalLongitude", result.getAssociatedCapitalLongitude());
        model.addAttribute("capitalLatitude", result.getAssociatedCapitalLatitude());

        return "result";
    }

}
