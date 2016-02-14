'use strict';

angular
    .module('App')
    .controller('MainController', ['httpRequest', function (httpRequest) { // Создание контроллера
        var vm = this;

        vm._init_ = function () { // Инициализация
            vm.query = { // Пример запрос
                    title: 'Parent', // Название
                    text: 'SELECT ?child ?parent WHERE {?child rela:childOf ?parent.}' // Sparql запрос
                };
            vm.output = 'Text will be here'; // Переменная вывода
        };

        vm.send = function (data) { // Функция отправки запроса
            httpRequest.send('PREFIX rela: <http://purl.org/vocab/relationship/>' + // Объявление префикса
             data.text) // Добавление запроса
             .then(function (response) {
                console.log(response);
                vm.output = response.data; // Присваивание вывода
            });
        };
    }]);