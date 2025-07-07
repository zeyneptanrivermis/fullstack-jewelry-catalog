import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductFilters, ProductService } from '../../services/product.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { debounceTime } from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.scss',
})
export class ProductListComponent implements OnInit {
  @ViewChild('carousel', { static: false })
  carousel!: ElementRef<HTMLDivElement>;
  products: Product[] = [];
  filters: ProductFilters = {};
  private scrollAmount = 280 + 24; // kart genişliği + boşluk

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts(this.filters).subscribe((data) => {
      this.products = data;
      this.carousel.nativeElement.scrollLeft = 0;
    });
  }

  applyFilters(): void {
    this.loadProducts();
  }

  clearFilters(): void {
    this.filters = {};
    this.loadProducts();
  }

  prev(): void {
    this.carousel.nativeElement.scrollBy({ left: -324, behavior: 'smooth' }); // 300 + 24 gap
  }

  next(): void {
    this.carousel.nativeElement.scrollBy({ left: 324, behavior: 'smooth' });
  }
}
