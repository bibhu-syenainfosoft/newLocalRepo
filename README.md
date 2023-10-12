app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AmazonHomeComponent } from './components/amazon-home/amazon-home.component';
import { AmazonJeweleryComponent } from './components/amazon-jewelery/amazon-jewelery.component';
import { AmazonMensComponent } from './components/amazon-mens/amazon-mens.component';
import { AmazonWomenComponent } from './components/amazon-women/amazon-women.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { ProductsComponent } from './components/products/products.component';
import { DetailsComponent } from './components/details/details.component';
import { ElectronicsComponent } from './components/electronics/electronics.component';
import { SearchComponent } from './components/search/search.component';
import { amazonRouteGuardGuard } from './Guards/amazon-route-guard.guard';

const routes: Routes = [
  {path: "home", component: AmazonHomeComponent},
  {path: "jewelery", component: AmazonJeweleryComponent},
  {path: "mens", component: AmazonMensComponent},
  {path: "women", component: AmazonWomenComponent},
  {path: "categories", component: CategoriesComponent,canActivate:[amazonRouteGuardGuard]},
  {path: "electronics", component: ElectronicsComponent},

  {path: "abc", component: ElectronicsComponent,outlet:"Bibhu"},
  {path: "abcdef", redirectTo:"categories"},
  {path: "search", component: SearchComponent},
  
  // {path: "products/:category", component: ProductsComponent},
  // {path: "details/:id", component: DetailsComponent},

  {path: "products/:category", component: ProductsComponent,
  children:[
  {path: "details/:id", component: DetailsComponent}
   ]},

  {path: "",  redirectTo:"home", pathMatch: "full" },
  {path: "**", component: NotfoundComponent} //Wild_Card Route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }









<h2 class="text-primary text-center">Params Data</h2>
<h4>Color:{{color}}</h4>
<h4>Price From:{{priceFrom}}</h4>
<h4>Price To:{{priceTo}}</h4>
<h4>Size:{{size}}</h4>






color='';
priceTo=0;
priceFrom=0;
size='M';

  constructor(private route:ActivatedRoute){
    this.route.queryParams.subscribe(params=>{
      console.log(params);
      this.color=params['color'];
      this.priceFrom=params['priceFrom'];
      this.priceTo=params['priceTo'];
      this.size=params['size'];
    });
}
}






