import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrl: './rating.component.scss',
})
export class RatingComponent {
  @Input() rating: number = 0;

  getStars(): number[] {
    const full = Math.floor(this.rating);
    const half = this.rating % 1 >= 0.5 ? 1 : 0;
    const stars = Array(full).fill(1);
    if (half) stars.push(0.5);
    return stars.concat(Array(5 - (full + half)).fill(0));
  }
}
