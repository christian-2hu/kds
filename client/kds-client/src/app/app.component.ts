import { Component, ViewChild } from '@angular/core';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';
import {
  NavigationCancel,
  NavigationEnd,
  NavigationError,
  NavigationStart,
  Router,
} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  @ViewChild(SidebarComponent) child!: SidebarComponent;

  public loading: boolean = true;
  public barsIcon = faBars;
  public title = 'kds-client';

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
      switch (event.constructor) {
        case NavigationStart: {
          this.loading = true;
          break;
        }
        case NavigationEnd: {
          this.loading = false;
          break;
        }
        case NavigationCancel:
        case NavigationError: {
          this.loading = false;
          break;
        }
        default: {
          break;
        }
      }
    });
  }

  public showSidebar() {
    this.child.showSidebar();
  }
}
