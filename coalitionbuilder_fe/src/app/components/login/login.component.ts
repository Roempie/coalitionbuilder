import {Component, inject} from '@angular/core';

import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import {CardModule} from "primeng/card";
import {DividerModule} from "primeng/divider";
import {ToastModule} from "primeng/toast";
import {ButtonModule} from "primeng/button";
import {NgClass, NgIf} from "@angular/common";
import {InputTextModule} from "primeng/inputtext";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {StorageService} from "../../services/storage.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CardModule,
    DividerModule,
    ToastModule,
    ButtonModule,
    ReactiveFormsModule,
    NgClass,
    FormsModule,
    InputTextModule,
    NgIf,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  private readonly storageService: StorageService = inject(StorageService);
  private readonly authService: AuthService = inject(AuthService);
  private readonly router = inject(Router);

  readonly form = new FormGroup({
    email: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.email,
      ]
    }),
    password: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(40)
      ]
    })
  })

  get f(): {[key: string]: AbstractControl} {
    return this.form.controls;
  }

  onSubmit(): void {
    if(this.form.invalid) {
      return;
    }

    this.authService.login(this.form.value.email!, this.form.value.password!).subscribe({
      next: (userDetails) => {
        this.storageService.saveUser(userDetails);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
