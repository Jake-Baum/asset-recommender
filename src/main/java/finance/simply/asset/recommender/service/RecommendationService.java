package finance.simply.asset.recommender.service;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.model.Customer;
import finance.simply.asset.recommender.model.Deal;
import finance.simply.asset.recommender.repository.CustomerRepository;
import finance.simply.asset.recommender.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

  private final DealRepository dealRepository;

  private final CustomerRepository customerRepository;

  private final AssetService assetService;

  @Autowired
  public RecommendationService(DealRepository dealRepository, CustomerRepository customerRepository,
                               AssetService assetService) {
    this.dealRepository = dealRepository;
    this.customerRepository = customerRepository;
    this.assetService = assetService;
  }

  public List<Asset> getRecommendations(Integer customerId) {
    List<Deal> deals = dealRepository.findAll();
    List<Customer> customers = customerRepository.findAll();

    List<Asset> recommendations = new ArrayList<>();

    for (Customer customer : customers) {
      if (customer.getId() == customerId) {
        List<Asset.Type> customerTypes = new ArrayList<>();

        for (Deal deal : deals) {
          if (deal.getCustomers().contains(customer)) {
            customerTypes.addAll(deal.getAssets().stream().map(asset -> asset.getType()).collect(Collectors.toList()));
          }
        }

        List<Asset> unsoldAssets = assetService.getUnsoldAssets();
        for (Asset asset : unsoldAssets) {
          if (customerTypes.contains(asset.getType())) {
            recommendations.add(asset);
          }
        }
      }
    }

    return recommendations;
  }

}