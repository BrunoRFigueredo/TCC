
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { ConfigService } from './services/service';
import { MenuComponent } from './components/menu/menu.component';
import { routing } from 'app.routes';
import { Http, HttpModule } from '@angular/http';
import { LoginComponent } from './components/login/login.component';
import { DadoRegistroComponent } from './components/dados/registro.component';

import { ClienteService } from './services/cliente/ClienteService';
import { EnderecoService } from './services/endereco/EnderecoService';
import { ApiService } from './services/login/service';
import { HttpClientModule } from '@angular/common/http';
import { ProfileUserComponent } from './components/profile/profile.component';
import { ImageUploadComponent } from './components/profile/image/image.component';
import { ImageService } from './services/image/image';
import { HomeComponent } from './components/home/home.component';
import { EditUserComponent } from './components/profile/edit';
import { GetTattoComponent } from './components/home/getonetattoo/get';
import { DadoTatComponent } from './components/dadostat/registro.component';
import { OrcRegistroComponent } from './components/orçamentos/registro.component';
import {NgxMaskModule, IConfig} from 'ngx-mask'
import { TatUserComponent } from './components/profile/soutat/edit';
import { AddUserComponent } from './components/cadastro/add';
import { OrcTatComponent } from './components/orçamentostat/registro.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomeComponent,
    LoginComponent,
    DadoRegistroComponent,
    OrcRegistroComponent,
    AddUserComponent,
    ProfileUserComponent,
    ImageUploadComponent,
    EditUserComponent,
    GetTattoComponent,
    DadoTatComponent,
    OrcTatComponent,
    TatUserComponent
  ], 
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    routing,
    ReactiveFormsModule,
    NgxMaskModule.forRoot()
  ],
  providers: [ConfigService,ClienteService,EnderecoService,ApiService,ImageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
