export interface LineItem {
  item: string
  quantity: number
  unitPrice: number
}

export interface PurchaseOrder {
  name: string
  address: string
  email: string
  delvieryDate: string
  rush: boolean
  lineItems: LineItem[]
}
