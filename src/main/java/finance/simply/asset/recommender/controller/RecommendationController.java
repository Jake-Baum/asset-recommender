package finance.simply.asset.recommender.controller;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation/{customerId}")
public class RecommendationController {

  private final RecommendationService recommendationService;

  @Autowired
  public RecommendationController(RecommendationService recommendationService) {
    this.recommendationService = recommendationService;
  }

  @GetMapping
  public ResponseEntity<List<Asset>> getRecommendations(@PathVariable Integer customerId) {
    List<Asset> recommendations = recommendationService.getRecommendations(customerId);
    return ResponseEntity.ok(recommendations);
  }

}