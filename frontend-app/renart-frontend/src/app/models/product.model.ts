export interface Product {
  name: string;
  popularityScore: number;
  weight: number;
  images: Record<string, string>;
  price: number;
  rating: number;
}
