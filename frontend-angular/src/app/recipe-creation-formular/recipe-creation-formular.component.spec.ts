import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeCreationFormularComponent } from './recipe-creation-formular.component';

describe('RecipeCreationFormularComponent', () => {
  let component: RecipeCreationFormularComponent;
  let fixture: ComponentFixture<RecipeCreationFormularComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecipeCreationFormularComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RecipeCreationFormularComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
