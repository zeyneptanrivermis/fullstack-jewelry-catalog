import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss',
})
export class ProductCardComponent implements OnInit {
  @Input() product!: Product;

  // Sipariş: sarı, gri(white), pembe(rose)
  colorKeys: Array<'yellow' | 'white' | 'rose'> = ['yellow', 'white', 'rose'];
  selectedColorKey: 'yellow' | 'white' | 'rose' = 'yellow';

  colorMap: Record<string, string> = {
    yellow: '#E6CA97',
    white:  '#D9D9D9',
    rose:   '#E1A4A9',
  };

  ngOnInit() {
    // Eğer product.images’daki anahtarlar farklıysa, override etme:
    if (this.product.images) {
      // varsayılanı yine sıramıza göre ayarla
      this.selectedColorKey = this.colorKeys.find(k => !!this.product.images[k])!;
    }
  }

  get selectedImage(): string {
    return this.product.images[this.selectedColorKey];
  }

  onSelectColor(key: 'yellow' | 'white' | 'rose'): void {
    this.selectedColorKey = key;
  }

  get displayRating(): string {
    return this.product.rating.toFixed(1);
  }

  getStars(rating: number): number[] {
    const full = Math.floor(rating);
    const half = rating % 1 >= 0.5 ? 1 : 0;
    const stars = Array(full).fill(1);
    if (half) stars.push(0.5);
    return stars.concat(Array(5 - (full + half)).fill(0));
  }
}
