import { Component, inject } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { StorageService } from '../../services/storage.service';
import { AuthService } from '../../services/auth.service';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import Validation from '../../validation/Validation';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    FormsModule,
    ButtonModule,
    RouterLinkActive,
    RouterLink,
    NgIf,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  private readonly storageService: StorageService = inject(StorageService);
  private readonly authService: AuthService = inject(AuthService);
  private readonly router = inject(Router);

  readonly form = new FormGroup({
    email: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [Validators.required, Validators.email],
    }),
    firstname: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [Validators.email],
    }),
    lastname: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [Validators.email],
    }),
    password: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(40),
      ],
    }),
    confirmPassword: new FormControl('', {
      updateOn: 'submit',
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(40),
      ],
    }),
  });

  onSubmit(): void {
    // if(!this.form.valid) {
    //   return;
    // }

    this.authService
      .register(
        this.form.value.email!,
        this.form.value.password!,
        this.form.value.firstname!,
        this.form.value.lastname!,
      )
      .subscribe({
        next: (userDetails) => {
          this.storageService.saveUser(userDetails);
          this.router.navigate(['/home']);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
}
