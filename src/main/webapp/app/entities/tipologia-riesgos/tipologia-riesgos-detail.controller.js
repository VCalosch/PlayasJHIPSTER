(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Tipologia_riesgosDetailController', Tipologia_riesgosDetailController);

    Tipologia_riesgosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tipologia_riesgos', 'Riesgos'];

    function Tipologia_riesgosDetailController($scope, $rootScope, $stateParams, previousState, entity, Tipologia_riesgos, Riesgos) {
        var vm = this;

        vm.tipologia_riesgos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('playasApp:tipologia_riesgosUpdate', function(event, result) {
            vm.tipologia_riesgos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
