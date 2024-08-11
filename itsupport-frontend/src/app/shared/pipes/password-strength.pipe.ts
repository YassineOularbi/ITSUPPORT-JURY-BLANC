import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'passwordStrength',
  standalone: true
})
export class PasswordStrengthPipe implements PipeTransform {

  transform(password: string): { strength: string, score: number } {
    let score = 0;

    if (!password) {
      return { strength: 'none', score: 0 };
    }

    if (password.length >= 8) score += 1;
    if (password.match(/[a-z]/)) score += 1;
    if (password.match(/[A-Z]/)) score += 1;
    if (password.match(/[0-9]/)) score += 1;
    if (password.match(/[^a-zA-Z0-9]/)) score += 1;

    let strength = 'none';
    if (score <= 2) strength = 'low';
    else if (score <= 4) strength = 'medium';
    else strength = 'strong';

    return { strength, score };
  }

}
