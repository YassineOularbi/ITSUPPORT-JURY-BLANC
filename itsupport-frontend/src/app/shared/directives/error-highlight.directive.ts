import { Directive, ElementRef, Input, Renderer2, SimpleChanges } from '@angular/core';

@Directive({
  selector: '[appErrorHighlight]',
  standalone: true
})
export class ErrorHighlightDirective {

  @Input() appErrorHighlight: boolean = false;

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['appErrorHighlight']) {
      if (this.appErrorHighlight) {
        this.setErrorStyles();
      } else {
        this.removeErrorStyles();
      }
    }
  }

  private setErrorStyles(): void {
    const input = this.el.nativeElement.querySelector('input');
    const label = this.el.nativeElement.querySelector('label');

    if (input) {
      this.renderer.setStyle(input, 'border-color', 'red');
      this.renderer.setStyle(input, 'color', 'red');
    }

    if (label) {
      this.renderer.setStyle(label, 'color', 'red');
    }
  }

  private removeErrorStyles(): void {
    const input = this.el.nativeElement.querySelector('input');
    const label = this.el.nativeElement.querySelector('label');

    if (input) {
      this.renderer.removeStyle(input, 'border-color');
      this.renderer.removeStyle(input, 'color');
    }

    if (label) {
      this.renderer.removeStyle(label, 'color');
    }
  }

}
