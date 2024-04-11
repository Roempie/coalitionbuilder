import {Component, inject} from '@angular/core';
import {StorageService} from "../../services/storage.service";
import {Router, RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  readonly storageService: StorageService = inject(StorageService);
  readonly authService: AuthService = inject(AuthService);
  readonly router: Router = inject(Router);

  onLogout() {
    this.storageService.clean();
    this.router.navigate(['/login']);
  }
}
