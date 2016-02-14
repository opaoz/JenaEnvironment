'use strict';

angular
    .module('App')
    .filter('codeFormatter', function () {
        return function (value) { // Функция фильтра
            if (angular.isObject(value) || angular.isArray(value)) {
                value = angular.toJson(value); // Если пришёль не JSON превращаем в JSON
            }

            if (!angular.isString(value)) {
                return value; // Если значение некорректно - возвращаем
            }

            return value.replace(/(\[|,|\{)/g, '$1\n') // Делаем строку мультилайновой
                .replace(/]/g, '\n]') // Добавляем переносы
                .replace(/"([a-z]+)":/g, '\t\t<code style="color:blue;">$1: </code>') // Перекрашиваем имя поля в синий
                .replace(/"http:\/\/family\/([a-z]+)"/g, '<code style="color:maroon">"$1"</code>') // Перекрашивае значение поля в красный
                .replace(/\{/g, '\t{') // Добавляем табуляции для открывающих скобок
                .replace(/}/g, '\n\t}'); // Добавляем табуляции для закрывающих скобок
        };
    });