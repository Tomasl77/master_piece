import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
    providedIn:"root"
})
export class Utils {

    constructor(private translateService : TranslateService) {

    }
    
    public  translate(stringToTranslate: string): string {
        return this.translateService.instant(stringToTranslate);
      }
}