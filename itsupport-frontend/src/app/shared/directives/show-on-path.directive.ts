import { Directive, Input, TemplateRef, ViewContainerRef, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';

@Directive({
  selector: '[appShowOnPath]',
  standalone: true
})
export class ShowOnPathDirective implements OnDestroy {
  private subscription: Subscription = new Subscription();
  
  @Input() set appShowOnPath(expectedPath: string) {
    this.subscription.unsubscribe();
    this.subscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateView(expectedPath);
      }
    });
    this.updateView(expectedPath);
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private vcRef: ViewContainerRef,
    private router: Router
  ) {}

  private updateView(expectedPath: string) {
    const currentPath = this.router.url;
    if (currentPath === expectedPath) {
      this.vcRef.createEmbeddedView(this.templateRef);
    } else {
      this.vcRef.clear();
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
