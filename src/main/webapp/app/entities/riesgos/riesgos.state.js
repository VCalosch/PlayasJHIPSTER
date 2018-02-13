(function() {
    'use strict';

    angular
        .module('playasApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('riesgos', {
            parent: 'entity',
            url: '/riesgos',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.riesgos.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/riesgos/riesgos.html',
                    controller: 'RiesgosController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('riesgos');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('riesgos-detail', {
            parent: 'riesgos',
            url: '/riesgos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.riesgos.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/riesgos/riesgos-detail.html',
                    controller: 'RiesgosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('riesgos');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Riesgos', function($stateParams, Riesgos) {
                    return Riesgos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'riesgos',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('riesgos-detail.edit', {
            parent: 'riesgos-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/riesgos/riesgos-dialog.html',
                    controller: 'RiesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Riesgos', function(Riesgos) {
                            return Riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('riesgos.new', {
            parent: 'riesgos',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/riesgos/riesgos-dialog.html',
                    controller: 'RiesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                detalle_riesgo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('riesgos', null, { reload: 'riesgos' });
                }, function() {
                    $state.go('riesgos');
                });
            }]
        })
        .state('riesgos.edit', {
            parent: 'riesgos',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/riesgos/riesgos-dialog.html',
                    controller: 'RiesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Riesgos', function(Riesgos) {
                            return Riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('riesgos', null, { reload: 'riesgos' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('riesgos.delete', {
            parent: 'riesgos',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/riesgos/riesgos-delete-dialog.html',
                    controller: 'RiesgosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Riesgos', function(Riesgos) {
                            return Riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('riesgos', null, { reload: 'riesgos' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
