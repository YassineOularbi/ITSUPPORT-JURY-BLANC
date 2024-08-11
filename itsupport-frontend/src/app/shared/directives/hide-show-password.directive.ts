import { Directive, ElementRef, HostListener, Renderer2, Input } from '@angular/core';

@Directive({
  selector: '[appHideShowPassword]',
  standalone: true
})
export class HideShowPasswordDirective {
  @Input('appHideShowPassword') passwordInput!: HTMLInputElement;
  @Input('appHideShowPasswordIcon') iconElement!: HTMLImageElement;

  private isPasswordVisible: boolean = false;

  constructor(private renderer: Renderer2) {}

  @HostListener('click')
  togglePasswordVisibility() {
    if (this.passwordInput && this.iconElement) {
      this.isPasswordVisible = !this.isPasswordVisible;
      const newType = this.isPasswordVisible ? 'text' : 'password';
      this.renderer.setAttribute(this.passwordInput, 'type', newType);

      const iconSrc = this.isPasswordVisible ? '/assets/show.png' : '/assets/hidden.png';
      this.renderer.setAttribute(this.iconElement, 'src', iconSrc);
    } else {
      console.error('Password input or icon element not found');
    }
  }
}
