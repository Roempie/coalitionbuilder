import {ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot} from "@angular/router";
import {inject} from "@angular/core";
import {StorageService} from "../services/storage.service";

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);
    if (storageService.isLoggedIn()) {
      // User is logged in, so return true
      return true;
    }
    router.navigate(['/login']);
    return false;
}

export const unAuthGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const storageService = inject(StorageService);
  const router = inject(Router);
  if (storageService.isLoggedIn()) {
    router.navigate(['/home']);
    return false;
  }
  return true;
}

