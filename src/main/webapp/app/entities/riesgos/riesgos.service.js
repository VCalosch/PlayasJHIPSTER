(function() {
    'use strict';
    angular
        .module('playasApp')
        .factory('Riesgos', Riesgos);

    Riesgos.$inject = ['$resource'];

    function Riesgos ($resource) {
        var resourceUrl =  'api/riesgos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
