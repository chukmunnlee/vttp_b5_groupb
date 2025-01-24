import {Injectable, inject} from "@angular/core";
import {OrderConfirmation, PurchaseOrder} from "./models";
import {firstValueFrom, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class PurchaseOrderService {

  private http = inject(HttpClient)

  createPO(po: PurchaseOrder): Promise<OrderConfirmation> {

    return firstValueFrom<OrderConfirmation>(
      this.http.post<OrderConfirmation>('/api/purchaseorder', po)
    )
  }
}
