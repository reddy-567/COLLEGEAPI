
//This controller is used for jsp files
package com.example.CollegeAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.CollegeAPI.Repository.CampusRepository;
import com.example.CollegeAPI.Repository.DepartmentRepository;
import com.example.CollegeAPI.Repository.SectionRepository;
import com.example.CollegeAPI.Repository.StudentRepository;
import com.example.CollegeAPI.dto.Branches;
import com.example.CollegeAPI.dto.StudentDetails;
import com.example.CollegeAPI.models.Campus;
import com.example.CollegeAPI.models.Department;
import com.example.CollegeAPI.models.Section;
import com.example.CollegeAPI.models.Student;

//@Controller
public class JSPController {
	
	@Autowired
	private CampusRepository c_repo;
	@Autowired
	public DepartmentRepository d_repo;
	@Autowired
	private SectionRepository s_repo;
	@Autowired
	private StudentRepository st_repo;
	
	@GetMapping("/home")
	public String home()
	{
		
		return "home.jsp";
		
	}
	
	@GetMapping("/campuses")
	public ModelAndView getCampuses(){
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("obj",c_repo.getCampusNames());
		mv.setViewName("campuses.jsp");
		return mv;
		//return c_repo.getCampusNames();
	}
	

	@GetMapping("/getDepartmentsById")
	public ModelAndView getAllDepartmentsById(@RequestParam int id)
	{
		ModelAndView mv=new ModelAndView("Departments.jsp");
		List<String> obj=c_repo.getBranchesByCampusId(id);
		//System.out.println(c_repo.getBranchesByCampusId(id));
		mv.addObject("obj",obj);
		mv.addObject("campus",c_repo.getOne(id));
		System.out.println("executes");
		System.out.println("obj is : "+obj);
		
		
		return mv;
		
	}
	
	@GetMapping("/getAllDepartments")
	public ModelAndView getAllDepartments()
	{
		ModelAndView mv=new ModelAndView("Departments.jsp");
		List<Branches> obj=c_repo.getJoinInformation();
		//System.out.println(c_repo.getBranchesByCampusId(id));
		mv.addObject("obj",obj);
		System.out.println("executes");
		System.out.println("obj is : "+obj);
		
		
		return mv;
		
	}
	
	@GetMapping("/getSectionsByDepartmentId")
	public ModelAndView getSections(@RequestParam int id)
	{
		ModelAndView mv=new ModelAndView("Sections.jsp");
		List<Section> obj=d_repo.getSectionsbyBranchId(id);
		//System.out.println(c_repo.getBranchesByCampusId(id));
		mv.addObject("obj",obj);
		mv.addObject("dep",d_repo.getOne(id));
		System.out.println("executes");
		System.out.println("obj is : "+obj);
		
		
		return mv;
		
	}
	
	@GetMapping("/getStudentsBySectionId")
	public ModelAndView getStudents(@RequestParam int id)
	{
		ModelAndView mv=new ModelAndView("Students.jsp");
		List<Student> obj=s_repo.getStudentsBySectionId(id);
		//System.out.println(c_repo.getBranchesByCampusId(id));
		mv.addObject("obj",obj);
		mv.addObject("section",s_repo.getOne(id));
		System.out.println("executes");
		System.out.println("obj is : "+obj);
		
		
		return mv;
		
	}
	
	@PostMapping("/addCampus")
	@ResponseBody
	public String addCampus(Campus campus)
	{
		 c_repo.save(campus);
		 return "Campus added Succesfully";
		//return "campus added successfully";
	}
	
	@PostMapping("/addDepartmentToCampus")
	@ResponseBody
	public Campus addCampus(@RequestParam int cid,Department department)
	{
		Campus c=c_repo.findById(cid).orElse(null);
		if(c!=null)
		{
			List<Department> l=c.getDepartments();
			l.add(department);
			c.setDepartments(l);
			
		return c_repo.save(c);
		}
		else
			return null;
		//return "campus added successfully";
	}
	
	@PostMapping("/addSectionToDepartment")
	@ResponseBody
	public Department addDepartment(@RequestParam int did,Section section)
	{
		Department d=d_repo.findById(did).orElse(null);
		if(d!=null)
		{
			List<Section> s=d.getSections();
			
			if(section.getStudents()!=null)
			section.setNoOfStudents(section.getStudents().size());
			else
				section.setNoOfStudents(0);
			s.add(section);
			d.setSections(s);
			return d_repo.save(d);
		}
		else
			return null;
	}
	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department dep)
	{
		return d_repo.save(dep);
		
	}
	
	
	//@GetMapping("/departments/{id}")
	//public List<Department> getDepartments(@PathVariable("id") int id)
	{
	//	return d_repo.findByCampuid_fk();
	}
	
	
	@GetMapping("/")
	@ResponseBody
	public List<Campus> getAllCampuses()
	{
		return c_repo.findAll();
	}
	
	@GetMapping("/campuses/departments")
	public List<Branches> getDepartments()
	{
		return c_repo.getJoinInformation();
	}
	
	@GetMapping("/departments/sections")
	public List<String> getDepartmentsAndSections()
	{
		return d_repo.getbranchesAndSections();
	}
	
	
	
	@GetMapping("/campus/{id}")
	public Campus getCampus(@PathVariable("id") int id)
	{
		return c_repo.findById(id).orElse(null);
	}
	
	@GetMapping("/section/{id}")
	public Section getSection(@PathVariable("id") int id)
	{
		return s_repo.findById(id).orElse(null);
	}

	@GetMapping("/students")
	public List<Student> getStudent()
	{
		return st_repo.findAll();
	}
	
	@GetMapping("/studentDetails")
	public List<StudentDetails> getStudentDetails()
	{
		return d_repo.getStudentDetails();
	}
	

	@GetMapping("/studentDetails/{id}")
	public StudentDetails getStudentDetails(@PathVariable("id") int id)
	{
		return d_repo.getStudentDetailsbyId(id);
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student)
	{
		return st_repo.save(student);
		
	}
	
	@PostMapping("/addStudentsToSection")
	@ResponseBody
	public Section addStudentsToSection(@RequestParam int sid,Student student)
	{
		Section s=s_repo.findById(sid).orElse(null);
		if(s!=null)
		{
			List<Student> l=s.getStudents();
			l.add(student);
			s.setStudents(l);
			s.setNoOfStudents(s.getStudents().size());
			return s_repo.save(s);
		}
		return null;
	}
	
	@DeleteMapping("/deleteSection/{id}")
	public String deleteSection(@PathVariable("id") int id)
	{
		try
		{
		s_repo.deleteById(id);
		return "section deleted successfully";
		}
		catch(Exception e)
		{
			return "Section is not exist with given id";
		}
	}
	
	@DeleteMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable("id") int id)
	{
		try
		{
		d_repo.deleteById(id);
		return "Department deleted successfully";
		}
		catch(Exception e)
		{
			return "Department is not exist with given id";
		}
	}
	
	@DeleteMapping("/deleteCampus/{id}")
	public String deleteCampus(@PathVariable("id") int id)
	{
		try
		{
		c_repo.deleteById(id);
		return "campus deleted successfully";
		}
		catch(Exception e)
		{
			return "campus is not exist with given id";
		}
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") int id)
	{
		try
		{
		st_repo.deleteById(id);
		return "student deleted successfully";
		}
		catch(Exception e)
		{
			return "Student is not exist with given id";
		}
	}

}
