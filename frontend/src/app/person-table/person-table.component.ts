import {Component, OnInit} from '@angular/core';
import {PersonServiceService} from '../services/person-service.service';
import {Person} from '../model/Person';

@Component({
  selector: 'app-person-table',
  templateUrl: './person-table.component.html',
  styleUrls: ['./person-table.component.css']
})
export class PersonTableComponent implements OnInit {

  persons: any;
  firstPage = true;
  lastPage = false;
  filter: string;

  allPersons: any;
  currentPersons: any;

  constructor(private personService: PersonServiceService) {
  }

  ngOnInit() {
    this.personService
      .getAllPersons()
      .subscribe(
        (persons) => {
          this.persons = persons;
          this.currentPersons = this.persons;
        }
      );

    this.personService
      .getCompletePersons()
      .subscribe(
        (persons) => {
          this.allPersons = persons;
        }
      );
  }

  paginationButtonClick(link: any) {
    console.log('Pagination Link: ' + link.href);
    this.personService
      .getPaginatedData(link.href)
      .subscribe(
        (persons) => {
          this.persons = persons;
          this.currentPersons = this.persons;
          this.updateVisibility();
        }
      );
  }

  search(event: any) {
    this.filter = event.target.value;
    console.log('String to Be Searched: ' + this.filter);
    if (this.filter) {
      this.persons = {_embedded:
            { persons: this.performFilter(this.filter) },
        page: {
          size: 0,
          totalElements: 0,
          totalPages: 1,
          number: 0
        }};
      this.updateVisibility();
    } else {
      console.log('String to Be Searched is null: ' + this.filter);
      this.persons = this.currentPersons;
      this.updateVisibility();
    }
  }

  private updateVisibility() {
    if (this.persons.page.number === 0) {
      this.firstPage = true;
    } else {
      this.firstPage = false;
    }
    if ((this.persons.page.number + 1) === this.persons.page.totalPages) {
      this.lastPage = true;
    } else {
      this.lastPage = false;
    }
    console.log('FirstPage: ' + this.firstPage);
    console.log('LastPage: ' + this.lastPage);
  }

  private performFilter(filterBy: string): Person[] {
    console.log('Performing filter');
    filterBy = filterBy.toLocaleLowerCase();
    return this.allPersons._embedded.persons.filter((person) =>
      ((person.name.toLocaleLowerCase().indexOf(filterBy) !== -1)
        || (person._embedded.address.country.toLocaleLowerCase().indexOf(filterBy) !== -1)
        || (person._embedded.address.streetName.toLocaleLowerCase().indexOf(filterBy) !== -1))
    );
  }
}
