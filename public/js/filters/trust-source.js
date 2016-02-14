'use strict';

angular
    .module('App')
    .filter('trust', function ($sce) {
        return function (value, source) { // Функция фильтра
            var result;
            switch (source.toLowerCase()) {
                case 'url':
                    result = $sce.trustAsResourceUrl(value); // Ресурс как URL
                    break;
                case 'html':
                    result = $sce.trustAsHtml(value); // Ресурс как HtmL разметка
                    break;
                case 'js':
                    $sce.trustAsJs(value); // Ресурс как JS код
                    break;
                default:
                    result = value;
            }
            return result;
        };
    });
