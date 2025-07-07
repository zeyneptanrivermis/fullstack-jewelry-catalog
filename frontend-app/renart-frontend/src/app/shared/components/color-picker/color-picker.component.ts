import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-color-picker',
  templateUrl: './color-picker.component.html',
  styleUrl: './color-picker.component.scss',
})
export class ColorPickerComponent {
  @Input() selectedColorKey!: 'yellow' | 'white' | 'rose';
  @Output() colorChange = new EventEmitter<'yellow' | 'white' | 'rose'>();

  readonly colorKeys: Array<'yellow' | 'white' | 'rose'> = [
    'yellow',
    'white',
    'rose',
  ];

  onSelectColor(key: 'yellow' | 'white' | 'rose') {
    this.colorChange.emit(key);
  }
}
