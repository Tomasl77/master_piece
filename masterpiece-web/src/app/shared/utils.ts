import { TranslateService } from '@ngx-translate/core';

export default class Utils {

    static translateService: TranslateService;

    constructor(public translateService : TranslateService) {
        Utils.translateService = translateService;
    }

    static translateString(string : string) {
        return this.translateService.instant(string);
    }
}