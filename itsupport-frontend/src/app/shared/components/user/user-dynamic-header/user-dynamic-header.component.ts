import { CommonModule } from '@angular/common';
import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterLink, RouterModule } from '@angular/router';
import { ShowOnPathDirective } from '../../../directives/show-on-path.directive';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-dynamic-header',
  standalone: true,
  imports: [
    CommonModule, 
    ShowOnPathDirective, 
    RouterLink,
    RouterModule
  ],
  templateUrl: './user-dynamic-header.component.html',
  styleUrls: ['./user-dynamic-header.component.css']
})
export class UserDynamicHeaderComponent {

}
