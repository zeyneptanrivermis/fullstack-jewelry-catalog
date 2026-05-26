import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';
import { environment } from '../../environments/environment';

export interface ProductFilters {
  priceMin?: number;
  priceMax?: number;
  popularityMin?: number;
  popularityMax?: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = `${environment.apiUrl}/api/products`;

  constructor(private http: HttpClient) {}

  getProducts(filters?: ProductFilters): Observable<Product[]> {
    let params = new HttpParams();

    // Sadece dolu olan filtreleri ekle
    if (filters) {
      if (filters.priceMin != null)      { params = params.set('priceMin', filters.priceMin.toString()); }
      if (filters.priceMax != null)      { params = params.set('priceMax', filters.priceMax.toString()); }
      if (filters.popularityMin != null) { params = params.set('popularityMin', filters.popularityMin.toString()); }
      if (filters.popularityMax != null) { params = params.set('popularityMax', filters.popularityMax.toString()); }
    }

    return this.http.get<Product[]>(this.apiUrl, { params });
  }
}
