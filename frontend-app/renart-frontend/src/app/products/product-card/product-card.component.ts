import { Component, Input } from '@angular/core';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss',
})
export class ProductCardComponent {
  @Input() product!: Product;

  colorKeys: string[] = [];
  selectedColorKey!: string;

  colorMap: Record<string,string> = {
    yellow: '#FFD700',
    rose:   '#FFC0CB',
    white:  '#FFFFFF'
  };

  ngOnInit() {
    this.colorKeys = Object.keys(this.product.images);
    this.selectedColorKey = this.colorKeys[0];
  }

  get selectedImage(): string {
    return this.product.images[this.selectedColorKey];
  }

  onSelectColor(key: string): void {
    this.selectedColorKey = key;
  }

  get displayRating(): string {
    return this.product.rating.toFixed(1);
  }
}
