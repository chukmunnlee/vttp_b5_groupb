export interface LineItem {
  name: string
  quantity: number
  unitPrice: number
}

export interface PurchaseOrder {
  name: string
  address: string
  deliveryDate: string
  lineItems: LineItem[]
}

export interface OrderConfirmation {
  poId: string
}
