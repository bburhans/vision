
package Names is

   subtype Domain_Name is String
     with Dynamic_Predicate => Is_Domain_Name(Domain_Name);

   function Is_Domain_Name(Name : String) return Boolean;


   -- subtype Email_Address is String
   --   with Dynamic_Predicate => Is_Email_Address(Email_Address);
   --
   -- function is_Email_Address(Address : String) return Boolean;

end Names;
