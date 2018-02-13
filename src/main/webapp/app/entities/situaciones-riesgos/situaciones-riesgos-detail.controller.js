(function() {
    'use strict';

    angular
        .module('playasApp')
        .controller('Situaciones_riesgosDetailController', Situaciones_riesgosDetailController);

    Situaciones_riesgosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Situaciones_riesgos'];

    function Situaciones_riesgosDetailController($scope, $rootScope, $stateParams, previousState, entity, Situaciones_riesgos) {
        var vm = this;

        vm.situaciones_riesgos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('playasApp:situaciones_riesgosUpdate', function(event, result) {
            vm.situaciones_riesgos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
