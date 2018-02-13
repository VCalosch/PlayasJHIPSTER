(function() {
    'use strict';
    angular
        .module('playasApp')
        .factory('Tipologia_riesgos', Tipologia_riesgos);

    Tipologia_riesgos.$inject = ['$resource'];

    function Tipologia_riesgos ($resource) {
        var resourceUrl =  'api/tipologia-riesgos/:id';

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
