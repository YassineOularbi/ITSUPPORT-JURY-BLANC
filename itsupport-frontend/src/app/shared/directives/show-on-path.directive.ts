import { Directive, Input, TemplateRef, ViewContainerRef, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs';

@Directive({
  selector: '[appShowOnPath]',
  standalone: true
})
export class ShowOnPathDirective implements OnDestroy {
  private subscription: Subscription = new Subscription();
  private viewCreated = false;

  @Input() set appShowOnPath(paths: string | string[]) {
    this.subscription.unsubscribe();

    const pathArray = typeof paths === 'string' ? paths.split('|').map(p => p.trim()) : paths;

    this.subscription = this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateView(pathArray);
      }
    });
    this.updateView(pathArray);
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private vcRef: ViewContainerRef,
    private router: Router
  ) {}

  private updateView(expectedPaths: string[]) {
    const currentPath = this.router.url;
    const matches = expectedPaths.some(path => currentPath.includes(path));

    if (matches && !this.viewCreated) {
      this.vcRef.createEmbeddedView(this.templateRef);
      this.viewCreated = true;
    } else if (!matches && this.viewCreated) {
      this.vcRef.clear();
      this.viewCreated = false;
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.vcRef.clear();
  }
}
