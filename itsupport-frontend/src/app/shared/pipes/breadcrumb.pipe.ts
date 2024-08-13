import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'breadcrumb',
  standalone: true
})
export class BreadcrumbPipe implements PipeTransform {
  transform(path: string): string {
    const segments = path.split('/').filter(segment => segment.length > 0);
    const formattedSegments = segments.map(segment => 
      segment.charAt(0).toUpperCase() + segment.slice(1)
    );
    
    return formattedSegments.join(' / ');
  }
}
