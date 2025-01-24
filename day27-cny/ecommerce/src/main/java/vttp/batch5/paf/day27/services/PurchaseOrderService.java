package vttp.batch5.paf.day27.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import vttp.batch5.paf.day27.models.PurchaseOrder;

@Service
public class PurchaseOrderService {

  public String createPurchaseOrder(PurchaseOrder po) {
    String poId = UUID.randomUUID().toString().substring(0, 8);

    return poId;
  }

}
