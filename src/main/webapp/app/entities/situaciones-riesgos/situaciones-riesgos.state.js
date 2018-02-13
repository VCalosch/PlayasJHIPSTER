(function() {
    'use strict';

    angular
        .module('playasApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('situaciones-riesgos', {
            parent: 'entity',
            url: '/situaciones-riesgos',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.situaciones_riesgos.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/situaciones-riesgos/situaciones-riesgos.html',
                    controller: 'Situaciones_riesgosController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('situaciones_riesgos');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('situaciones-riesgos-detail', {
            parent: 'situaciones-riesgos',
            url: '/situaciones-riesgos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'playasApp.situaciones_riesgos.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/situaciones-riesgos/situaciones-riesgos-detail.html',
                    controller: 'Situaciones_riesgosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('situaciones_riesgos');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Situaciones_riesgos', function($stateParams, Situaciones_riesgos) {
                    return Situaciones_riesgos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'situaciones-riesgos',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('situaciones-riesgos-detail.edit', {
            parent: 'situaciones-riesgos-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/situaciones-riesgos/situaciones-riesgos-dialog.html',
                    controller: 'Situaciones_riesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Situaciones_riesgos', function(Situaciones_riesgos) {
                            return Situaciones_riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('situaciones-riesgos.new', {
            parent: 'situaciones-riesgos',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/situaciones-riesgos/situaciones-riesgos-dialog.html',
                    controller: 'Situaciones_riesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id_registro_riesgo: null,
                                tipo_variable: null,
                                direccion: null,
                                oleaje_tamano: null,
                                vieno_velocidad: null,
                                marea_altura: null,
                                marea_rango: null,
                                marea_momento: null,
                                probabilidad: null,
                                severidad: null,
                                evaluacion: null,
                                fecha: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('situaciones-riesgos', null, { reload: 'situaciones-riesgos' });
                }, function() {
                    $state.go('situaciones-riesgos');
                });
            }]
        })
        .state('situaciones-riesgos.edit', {
            parent: 'situaciones-riesgos',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/situaciones-riesgos/situaciones-riesgos-dialog.html',
                    controller: 'Situaciones_riesgosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Situaciones_riesgos', function(Situaciones_riesgos) {
                            return Situaciones_riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('situaciones-riesgos', null, { reload: 'situaciones-riesgos' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('situaciones-riesgos.delete', {
            parent: 'situaciones-riesgos',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/situaciones-riesgos/situaciones-riesgos-delete-dialog.html',
                    controller: 'Situaciones_riesgosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Situaciones_riesgos', function(Situaciones_riesgos) {
                            return Situaciones_riesgos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('situaciones-riesgos', null, { reload: 'situaciones-riesgos' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
