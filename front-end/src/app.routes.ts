import { ModuleWithProviders, Component }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './app/components/home/home.component';
import { LoginComponent } from 'app/components/login/login.component';

import { DadoRegistroComponent } from 'app/components/dados/registro.component';
import { ProfileUserComponent } from 'app/components/profile/profile.component';
import { ImageUploadComponent } from 'app/components/profile/image/image.component';
import { EditUserComponent } from 'app/components/profile/edit';
import { GetTattoComponent } from 'app/components/home/getonetattoo/get';
import { DadoTatComponent } from 'app/components/dadostat/registro.component';
import { OrcRegistroComponent } from 'app/components/orçamentos/registro.component';
import { TatUserComponent } from 'app/components/profile/soutat/edit';
import { AddUserComponent } from 'app/components/cadastro/add';
import { OrcTatComponent } from 'app/components/orçamentostat/registro.component';


 
const appRoutes: Routes = [

    { path: 'home',                    component: HomeComponent },
    { path: 'registrou',                component: AddUserComponent},
    { path: 'login',                   component: LoginComponent},
    { path: 'tabeladt',             component: DadoTatComponent},
    { path: 'tabeladc',             component: DadoRegistroComponent},
    { path: 'tabelaot',             component: OrcRegistroComponent},
    { path: 'tabelaoc',             component: OrcTatComponent},
    { path: 'profile',                component: ProfileUserComponent},
    { path: 'image',                  component: ImageUploadComponent},
    { path: 'login', component: LoginComponent },
    { path: 'add-user', component: AddUserComponent },
    { path: 'soutat', component: TatUserComponent },
    { path: 'edit', component: EditUserComponent },
    { path: 'get/:id', component: GetTattoComponent },
    {path : '', component : HomeComponent}



];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);