package com.u14n.sandbox.spring.rest.service;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u14n.sandbox.model.DAOException;
import com.u14n.sandbox.model.Location;
import com.u14n.sandbox.model.LocationDAO;

@Controller("locationsService")
@RequestMapping("/restapi")
public class LocationsService {

	private LocationDAO locationDAO = new LocationDAO.ConcurrentMemory();

	public LocationsService() {
		try {
			this.locationDAO.insert(new Location(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"500 S McDowell St"));
			this.locationDAO.insert(new Location(
					"USA",
					"NC",
					"27601",
					"Raleigh",
					"100 E Davie Street"));
		} catch (DAOException e) {
			// Ignore
		}
																				System.out.println("LocationsService.LocationsService()");
	}

	@RequestMapping(
			value = "/locations/{id}",
			method = RequestMethod.GET,
			headers = "Accept=application/json",
			produces = { "application/json" })
	@ResponseBody
	public Location getLocation(@PathVariable int id)
			throws DAOException {
																				System.out.println("LocationsService.getLocation() id=" + id);
		return this.locationDAO.findByIdentity(id);
	}

	@RequestMapping(
			value = "/locations.html",
			method = RequestMethod.GET,
			headers = "Accept=text/html",
			produces = { "text/html" })
	@ResponseBody
	public String getLocationListHTML() 
			throws DAOException {
																				System.out.println("LocationsService.getLocationListHTML()");
		String retVal = "<html><body><table border=1>";
		List<Location> locations = this.locationDAO.findAll();
		for (Location location : locations) {
			retVal += "<tr><td>" + location.getId() +
					"</td><td>" + location.getCountryCode() +
					"</td><td>" + location.getRegionCode() +
					"</td><td>" + location.getPostalCode() +
					"</td><td>" + location.getCity() +
					"</td><td>" + location.getStreet() +
					"</td></tr>";
		}
		retVal += "</table></body></html>";

		return retVal;
	}

	@RequestMapping(
			value = "/locations/list",
			method = RequestMethod.GET,
			headers = "Accept=application/json",
			produces = { "application/json" })
	@ResponseBody
	public List<Location> getLocationList() throws DAOException {
																				System.out.println("LocationsService.getLocationList()");
		return this.locationDAO.findAll();
	}

	@RequestMapping(
			value = "/locations",
			method = RequestMethod.HEAD)
	@ResponseBody
	public void headLocations() {
																				System.out.println("LocationsService.headLocations()");
	}

	@RequestMapping(
			value = "/locations",
			method = RequestMethod.GET,
			headers = "Accept=application/json",
			produces = { "application/json" })
	@ResponseBody
	public List<Location> getLocations() throws DAOException {
																				System.out.println("LocationsService.getLocations()");
		return this.locationDAO.findAll();
	}

	@RequestMapping(
			value = "/locations.json",
			method = RequestMethod.GET,
			headers = "Accept=application/json",
			produces = { "application/json" })
	@ResponseBody
	public List<Location> getLocationsJson() throws DAOException {
																				System.out.println("LocationsService.getLocationsJson()");
		return this.locationDAO.findAll();
	}

	@RequestMapping(
			value = "/locations.xml",
			method = RequestMethod.GET,
//			headers = "Accept=text/xml",
			produces = { "text/xml" })
	@ResponseBody
	public List<Location> getLocationsXml() throws DAOException {
																				System.out.println("LocationsService.getLocationsXml()");
		return this.locationDAO.findAll();
	}

	@RequestMapping(
			value = "/locations/{id}",
			method = RequestMethod.PUT,
			headers = "Accept=application/json",
			produces = { "application/json" },
			consumes = { "application/json" })
	@ResponseBody
	public Location updateLocation(
			@RequestBody Location newLocation,
			@PathVariable int id) throws DAOException {
																				System.out.println("LocationsService.updateLocation()");
		List<Location> locations = this.locationDAO.findAll();
		for (Location location : locations) {
			if (id == location.getId()) {
				location.setCountryCode(location.getCountryCode());
				location.setRegionCode(location.getRegionCode());
				location.setPostalCode(location.getPostalCode());
				location.setCity(location.getCity());
				location.setStreet(location.getStreet());
				return location;
			}
		}
		return null;
	}

	@RequestMapping(
			value = "/locations/{id}",
			method = RequestMethod.DELETE,
			headers = "Accept=application/json",
			produces = { "application/json" })
	@ResponseBody
	public boolean deleteLocation(@PathVariable int id) throws DAOException {
																				System.out.println("LocationsService.deleteLocation() id=" + id);
		List<Location> locations = this.locationDAO.findAll();
		for (Location location : locations) {
			if (location.getId() == id) {
				this.locationDAO.delete(location);
				return true;
			}
		}
		return false;
	}

	@RequestMapping(
			value = "/locations",
			method = RequestMethod.POST,
			headers = "Accept=application/json",
			produces = { "application/json" },
			consumes = { "application/json" })
	@ResponseBody
	public boolean createLocation(@RequestBody Location location)
			throws DAOException {
																				System.out.println("LocationsService.createLocation() location=" + location);
		this.locationDAO.insert(location);
		return true;
	}
}
