export interface Inventory {
  key: string
  image: string
  name: string
  unitPrice: number
}

export const INVENTORY: Inventory[] = [
  {
    key: 'apple',
    image: 'apple.png',
    name: 'Apple',
    unitPrice: 0.5
  },
  {
    key: 'blueberries',
    image: 'blueberries.png',
    name: 'Blueberries',
    unitPrice: 3.5
  },
  {
    key: 'corn',
    image: 'corn.png',
    name: 'Corn',
    unitPrice: .75
  },
  {
    key: 'potato',
    image: 'potato.png',
    name: 'Potato',
    unitPrice: 2
  },
  {
    key: 'pumpkin',
    image: 'pumpkin.png',
    name: 'Pumpkin',
    unitPrice: 2
  },
  {
    key: 'zucchini',
    image: 'zucchini.png',
    name: 'Zucchini',
    unitPrice: 4
  }
]
