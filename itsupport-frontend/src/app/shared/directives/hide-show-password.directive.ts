import { Directive, HostListener, Renderer2, Input } from '@angular/core';

@Directive({
  selector: '[appHideShowPassword]',
  standalone: true
})
export class HideShowPasswordDirective {
  @Input('appHideShowPassword') passwordInput!: HTMLInputElement;
  @Input('appHideShowPasswordIcon') iconElement!: HTMLImageElement;
  @Input('appHideShowPasswordButton') buttonElement!: HTMLButtonElement;

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
    }
  }

  @HostListener('input')
  onInput() {
    if (this.passwordInput && this.buttonElement) {
      const show = this.passwordInput.value.length > 0;
      this.renderer.setStyle(this.buttonElement, 'display', show ? 'block' : 'none');
    }
  }
  
}
