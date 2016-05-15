
with Ada.Characters.Handling;
use Ada.Characters.Handling;

package body Names is

   -- The syntax of domain names is covered in section 2.3.1 of RFC-1035. See, for example,
   -- https://tools.ietf.org/html/rfc1035. To summarize, the rules are as follows.
   --
   -- 1, A domain name consists of one or more labels separated by dots (no trailing dot).
   -- 2. A label consists of letters, digits, or dashes.
   -- 3. A label must start with a letter.
   -- 4. A label cannot end with a dash.
   -- 5. A label must be between 1 and 63 characters (inclusive) in length.
   --
   function Is_Domain_Name(Name : String) return Boolean is
      type State_Type is (Label_Start, Normal, Dash);

      State : State_Type;
      Ch : Character;
      Label_Length : Natural;
   begin
      State := Label_Start;
      Label_Length := 0;

      for I in Name'Range loop
         Ch := Name(I);

         -- This test ensures any "letters" we find are ASCII letters.
         if Ch not in ISO_646 then
            return False;
         end if;

         case State is
            when Label_Start =>
               if not Is_Letter(Ch) then
                  return False;
               else
                  Label_Length := Label_Length + 1;
                  State := Normal;
               end if;

            when Normal =>
               if Ch = '.' then
                  if Label_Length < 1 or Label_Length > 63 then
                     return False;
                  end if;
                  State := Label_Start;
                  Label_Length := 0;
               elsif Ch = '-' then
                  State := Dash;
                  Label_Length := Label_Length + 1;
               elsif not Is_Letter(Ch) then
                  return False;
               else
                  Label_Length := Label_Length + 1;
               end if;

            when Dash =>
               if Ch = '-' then
                  Label_Length := Label_Length + 1;
               elsif not Is_Letter(Ch) then
                  return False;
               else
                  State := Normal;
                  Label_Length := Label_Length + 1;
               end if;

         end case;
      end loop;

      -- Now examine the state at the end.
      case State is
         -- The last character was a dot or the name was entirely empty.
         when Label_Start =>
            return False;

         when Normal =>
            null;

         -- The last character was a dash and dashes are not allowed here.
         when Dash =>
            return False;
      end case;

      return True;
   end Is_Domain_Name;

end Names;
