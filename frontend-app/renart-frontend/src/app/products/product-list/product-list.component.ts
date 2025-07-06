import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss',
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  @ViewChild('carousel', { read: ElementRef }) carousel!: ElementRef;

  private scrollAmount = 280 + 24; // kart genişliği + margin

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService
      .getProducts()
      .subscribe((data) => (this.products = data));
  }

  next(): void {
    this.carousel.nativeElement.scrollBy({
      left: this.scrollAmount,
      behavior: 'smooth',
    });
  }
  prev(): void {
    this.carousel.nativeElement.scrollBy({
      left: -this.scrollAmount,
      behavior: 'smooth',
    });
  }
}
