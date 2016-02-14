'use strict';

angular
   .module('App')
   .service('httpRequest', ['$http', 'config', function ($http, config) { //Создание сервиса
       this.send = function (message) { // Метод сервиса
           return $http({ // Отправка запроса
               url: config.queryUrl, // URL из конфига
               method: 'POST', // Метод ПОСТ
               headers: { // Хедеры для Jena
                   'Content-Type': 'application/x-www-form-urlencoded',
               },
               data: message // Сообщение
           });
       }
   }]);