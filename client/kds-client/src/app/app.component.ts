import { Component, ViewChild } from '@angular/core';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  @ViewChild(SidebarComponent) child!: SidebarComponent;

  public barsIcon = faBars;
  public title = 'kds-client';

  public showSidebar() {
    this.child.showSidebar();
  }
}
